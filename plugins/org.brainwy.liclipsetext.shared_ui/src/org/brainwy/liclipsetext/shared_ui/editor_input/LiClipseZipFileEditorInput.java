/**
 * Copyright (c) 2005-2012 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Eclipse Public License (EPL).
 * Please see the license.txt included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package org.brainwy.liclipsetext.shared_ui.editor_input;

import java.io.File;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.PlatformUI;

/**
 * This editor input enables Eclipse to open and show the contents of a file within a zip file.
 *
 * @author Fabio
 */
public class LiClipseZipFileEditorInput implements IStorageEditorInput, IPathEditorInput, IPersistableElement {

    /**
     * This is the file that we're wrapping in this editor input.
     */
    private final LiClipseZipFileStorage storage;

    public LiClipseZipFileEditorInput(LiClipseZipFileStorage storage) {
        this.storage = storage;
    }

    @Override
    public IStorage getStorage() throws CoreException {
        return this.storage;
    }

    public File getFile() {
        return this.storage.zipFile;
    }

    public String getZipPath() {
        return this.storage.zipPath;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        IEditorRegistry registry = PlatformUI.getWorkbench().getEditorRegistry();
        return registry.getImageDescriptor(getContentType());
    }

    @Override
    public String getName() {
        return this.storage.getName();
    }

    @Override
    public IPersistableElement getPersistable() {
        return this;
    }

    public String getContentType() {
        return this.storage.getFullPath().getFileExtension();
    }

    @Override
    public String getToolTipText() {
        IPath fullPath = storage.getFullPath();
        if (fullPath == null) {
            return null;
        }
        return fullPath.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getAdapter(Class<T> adapter) {
        if (adapter.isInstance(this)) {
            return (T) this;
        }
        return null;
    }

    @Override
    public IPath getPath() {
        return storage.getFullPath();
    }

    @Override
    public void saveState(IMemento memento) {
        LiClipseEditorInputFactory.saveState(memento, this);
    }

    @Override
    public String getFactoryId() {
        return LiClipseEditorInputFactory.FACTORY_ID;
    }

    // It seems that it's not possible to define an URI to an element inside a zip file,
    // so, we can't properly implement ILocationProvider nor ILocationProviderExtension (meaning that the document connect
    // needs to be overridden to deal with external files).
    //
    //    public IPath getPath(Object element) {
    //        if(element instanceof LiClipseZipFileEditorInput){
    //            LiClipseZipFileEditorInput editorInput = (LiClipseZipFileEditorInput) element;
    //            return editorInput.getPath();
    //
    //        }
    //        return null;
    //    }
    //
    //    public URI getURI(Object element) {
    //        if(element instanceof LiClipseZipFileEditorInput){
    //            try {
    //                LiClipseZipFileEditorInput editorInput = (LiClipseZipFileEditorInput) element;
    //                URL url = editorInput.storage.zipFile.toURI().toURL();
    //                String externalForm = url.toExternalForm();
    //                return new URL("zip:"+externalForm+"!"+editorInput.storage.zipPath).toURI();
    //            } catch (Exception e) {
    //                Log.log(e);
    //            }
    //
    //        }
    //        return null;
    //    }

}
