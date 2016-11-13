package com.test.mavenDemo;

/**
 * Hello world!
 
 */

public class App 
{
    public static void main( String[] args )
    
    {
    	Animal[] animal=new Animal[2];
    	animal[0]=new Animal("apa");
    	animal[1]=new Animal("häst");
    	System.out.println(animal[0].getId()+" "+animal[0].getName());
    	System.out.println(animal[1].getId()+" "+animal[1].getName());
        System.out.println( "Hello World!" );
    }
}
