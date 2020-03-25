/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.bewarethekraken;

/**
 *
 * @author Patricia
 */
public class BewareTheKraken {
    public static void main(String[] args) {
        System.out.println("Already, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooOOooOooo.....! *SPLASH*");
        
        int depthDivedInFt = 0;
        
        while (depthDivedInFt < 36200){
            System.out.println("So far, we've swam " + depthDivedInFt + " feet");
            
            if (depthDivedInFt >= 20000){
                System.out.println("Uhhh, I think I see a Kraken, guys ....");
                System.out.println("TIME TO GO!");
                break;
            }
            
            depthDivedInFt += 1000;
        }
            System.out.println("");
            System.out.println("We ended up swimming " + depthDivedInFt + " feet down.");
            System.out.println("I bet we can do better next time!");
    }    
}
