package com.consoles;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ViewManager {
    private static ViewManager viewManager;
    private boolean running;
    private final Scanner scanner;

    //When adapting this in to P0, don't forget to replace this with your custom list structure
    List<View> viewList;
    View nextView;

    private ViewManager() {
        //set up starting values and references
        running = true;
        scanner = new Scanner(System.in);
        viewList = new LinkedList<View>();
    }

    public static ViewManager getViewManager(){
        if(viewManager == null) {
            viewManager = new ViewManager();
        }
        return viewManager;
    }

    public void navigate(String destination) {
        for(View view : viewList) {
            if(view.viewName.equals(destination)){
                nextView = view;
                break;
            }
        }
    }

    public void registerView(View view) {
        viewList.add(view);
    }

    public void render() {
        nextView.renderView();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void quit() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

}