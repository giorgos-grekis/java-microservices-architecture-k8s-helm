//package com.cisu.breaking.bank.gatewayserver.service;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.stereotype.Service;
//import jakarta.annotation.PostConstruct;
//
//@Service
//public class RouteViewerService {
//
//    private final RouteLocator routeLocator;
//
//    // Κάνουμε inject το RouteLocator που έχει συγκεντρώσει όλα τα routes
//    public RouteViewerService(RouteLocator routeLocator) {
//        this.routeLocator = routeLocator;
//    }
//
//    @PostConstruct
//    public void printAllRoutes() {
//        System.out.println("=== Φόρτωση Spring Cloud Gateway Routes ===");
//
//        routeLocator.getRoutes().subscribe(route -> {
//            System.out.println("Route ID: " + route.getId());
//            System.out.println("Target URI: " + route.getUri());
//            // Το predicate περιέχει την πληροφορία του path (π.χ. /breakingBank/accounts/**)
//            System.out.println("Predicate (Path): " + route.getPredicate());
//            System.out.println("------------------------------------------------");
//        });
//    }
//}