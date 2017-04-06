package roughprimefinder;

import java.util.ArrayList;

/**
 * Program to find prime numbers
 * NOTE: Numbers found by ruling out numbers that are not prime.
 *       False positives expected
 * @author Edward FitzSimons
 */
public class RoughPrimeFinder {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<Integer> list = makePrimeList(0,100,-1);

        System.out.println("\n\nNumber of primes smaller than 100,000:");
        list = makePrimeList(0,100000,-1);
        System.out.println(list.size());
    }
    
    public static void printPrimes(ArrayList<Integer> primes){
        for(Integer i: primes){
            System.out.println(i);
        }
    }

    /**
     * Makes a list of prime numbers with given requirements
     * @param start
     * @param end ignored when -1
     * @param count ignored when -1
     * @return 
     */
    public static ArrayList<Integer> makePrimeList(int start, int end, int count){
        
        ArrayList<Integer> primes = new ArrayList();
        ArrayList<Integer> checker = new ArrayList();
        
        if(end > 0 && count > 0){ //counting by start/end bounds
            for(int i = (int) start; i < end && primes.size() < count; ++i){
                if(!checker.contains(i) && !isNotPrime(i)){
                    primes.add(i);
                    checker = expandPrimes(i, end, checker);
                }
            }
        }
        else if (end > 0){ //counting to value
            for(int i = (int) start; i < end; ++i){
                if(!checker.contains(i) && !isNotPrime(i)){
                    primes.add(i);
                    checker = expandPrimes(i, end, checker);
                }
            }
        }
        else{
            for(int i = (int) start; primes.size() < count; ++i){
                if(!checker.contains(i) && !isNotPrime(i)){
                    primes.add(i);
                }
            }
        }
        return primes;
    }
    
    /**
     * Finds whether or not a value is NOT prime
     * @param num
     * @return 
     */
    private static boolean isNotPrime(int num){
        
        boolean nprime = false;    
        for(int i = 1; i <= num/2 && !nprime; ++i){
            if(gcd(num, i) > 1){
                nprime = true;
            }
        }
        return nprime;
    }
    
    /**
     * Recursive function to find the gcd between two integers
     * @param base
     * @param div
     * @return 
     */
    private static int gcd(int num, int div){
        
        int rem = num % div;
        
        if(rem != 0){
            rem = gcd(div, rem);
        }
        else{
            rem = div;
        }

        return rem;
    }
    
    private static ArrayList<Integer> expandPrimes(int n, int max, ArrayList<Integer> primes){ 
        if(n != 0 && n != 1){
            System.out.println("Excluding multiples of " + n);
            int m = n;
            for(int i = 1; m < max; i++){
                m = n * i;
                primes.add(m);
            }
        }
        return primes;
    }
}
