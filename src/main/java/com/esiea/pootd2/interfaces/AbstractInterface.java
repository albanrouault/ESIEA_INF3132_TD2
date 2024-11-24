package com.esiea.pootd2.interfaces;

import com.esiea.pootd2.controllers.IExplorerController;

/**
 * Abstract class for the user interface.
 */
public abstract class AbstractInterface implements IUserInterface {
    protected IExplorerController controller;

    /**
     * Constructs an AbstractInterface with the given controller.
     *
     * @param controller The controller to use.
     */
    public AbstractInterface(IExplorerController controller) {
        this.controller = controller;
    }
}
