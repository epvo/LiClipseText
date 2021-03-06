/**
 * Copyright (c) 2005-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Eclipse Public License (EPL).
 * Please see the license.txt included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package org.brainwy.liclipsetext.shared_ui.quick_outline;

import org.brainwy.liclipsetext.shared_core.structure.DataAndImageTreeNode;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class DataAndImageTreeNodeContentProvider implements ITreeContentProvider {

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement == null) {
            return new Object[0];
        }
        @SuppressWarnings("rawtypes")
        DataAndImageTreeNode m = (DataAndImageTreeNode) parentElement;
        return m.childrenAsArray();
    }

    @Override
    public Object getParent(Object element) {
        @SuppressWarnings("rawtypes")
        DataAndImageTreeNode m = (DataAndImageTreeNode) element;
        return m.getParent();
    }

    @Override
    public boolean hasChildren(Object element) {
        @SuppressWarnings("rawtypes")
        DataAndImageTreeNode m = (DataAndImageTreeNode) element;
        return m.hasChildren();
    }

}
