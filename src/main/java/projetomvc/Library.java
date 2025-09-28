package projetomvc;

import projetomvc.config.ApplicationInitializer;

public class Library {
    public static void main(String[] args) {
        ApplicationInitializer appInit = new ApplicationInitializer();
        appInit.initialize();
    }
}
