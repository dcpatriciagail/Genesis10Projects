package com.tsguild.doggenetics;

import java.util.Scanner;
import java.util.Random;

public class DogGenetics {

    public static void main(String[] args) {
        Scanner name = new Scanner(System.in);
        Random percent = new Random();

        System.out.print("What is your dog's name? ");
        String dogName = name.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + dogName + "'s prestigious background right here.\n");
        System.out.println(dogName + " is:");

        String dogBreedArray[] = {"St. Bernard", "Chihuahua", "Dramatic Red Nosed Asian Pug", "Common Cur", "King Doberman"};

        int dogBreedPercentage; //will hold the random percentage
        int remainderPercentage = 100; 

        for (int i = 0; i < dogBreedArray.length; i++) {
            dogBreedPercentage = percent.nextInt(100);

            if (i == (dogBreedArray.length - 1)) {
                System.out.println(remainderPercentage + "% " + dogBreedArray[i]);
            } else {
                dogBreedPercentage = dogBreedPercentage * remainderPercentage / 100;
                System.out.println(dogBreedPercentage + "% " + dogBreedArray[i]);
                remainderPercentage = remainderPercentage - dogBreedPercentage;
            }

        }

        System.out.println("\nWow, that's QUITE the dog!");
    }
}
