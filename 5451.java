
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//spoj ANARC09C 
//involves prime-factorization, sieve, primality 
// to speed up prime factorization, calculate prime numbers in advance

class ANARC09C {

//store the primes initially to avoid calculating prime each time while doing factorization	
	private static int myPrimes[] ;
	public static boolean isPrime(long n){
		 if (n<=1) return false;
		   if (n==2) return true;
		   if (n%2==0) return false;
		   long m = (long)Math.sqrt(n)+1;

		   for (long i=3; i<=m; i+=2)
		      if (n%i==0)
		         return false;

		   return true;
		
	}
	
	public static int[] sieve(int n){
		boolean[] prime =  new boolean[n+1];
		Arrays.fill(prime, true);
		prime[0]=false;
		prime[1]=false;
		int upperLimit = (int)Math.sqrt(n);
		for(int i=2;i<upperLimit;i++){
			if(isPrime(i)){
				for(int j=i+i;j<=n;j+=i)
					prime[j]=false;
			}
		}
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i=1;i<=n;i++){
			if(prime[i])
				primes.add(i);
		}
		
		int result[] =new int[primes.size()];
		for(int i=0;i<result.length;i++){
			result[i]=primes.get(i);
		}
		return result;
		
		
	}

//function to do prime factorization
	public static int calculatePrimeFactors(int n, int[] primeFactors, int[] primeFactorExponents){

		
		if(n == 1)
			return 0;
		int num = n;
		int j=1;
		for(int i=0;i<myPrimes.length && myPrimes[i]<=num;i++){
			int div = myPrimes[i];
			int count=0;
			while(num%div == 0 ){
				count++;
				num=num/div;
			}
			if(count>0){
				primeFactors[j]=div;
				primeFactorExponents[j]=count;
				j++;
				
			}
			
		}
		return j-1;
		
		
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int a = 0 ;
		int b = 0;
		int[] primeFactors1 = new int[1001];
		int[] primeFactorExponents1 = new int[1001];
		
		int[] primeFactors2 = new int[1001];
		int[] primeFactorExponents2 = new int[1001];
		int testCaseCounter=0;
		myPrimes = sieve(1000000);
		for(a = s.nextInt(), b=s.nextInt(); a !=0 && b!=0; ){
			testCaseCounter++;
			int aRoot = (int)Math.sqrt(a)+1;
			
			Arrays.fill(primeFactors1, 0,aRoot,0);
			Arrays.fill(primeFactorExponents1, 0,aRoot,0);
			
			int nFactorsA = calculatePrimeFactors(a, primeFactors1, primeFactorExponents1);
			
			int bRoot = (int)Math.sqrt(b)+1;
			
			Arrays.fill(primeFactors2, 0,bRoot,0);
			Arrays.fill(primeFactorExponents2, 0,bRoot,0);
			
			int nFactorsB = calculatePrimeFactors(b, primeFactors2, primeFactorExponents2);
			
			int minOrdinate = 0;
			
			int distance=0;
			int i=1,j=1;
			for(;i<=nFactorsA && j<=nFactorsB;){
				if(primeFactors1[i] == primeFactors2[j]){
					distance+=Math.abs(primeFactorExponents1[i]-primeFactorExponents2[j]);
					i++; j++;minOrdinate++;
				} else if ( primeFactors1[i] <  primeFactors2[j]){
					distance+=primeFactorExponents1[i];
					i++;minOrdinate++;
				}else{
					distance+=primeFactorExponents2[j];
					j++;minOrdinate++;
				}
			}
			//to account for case when one of the numbers is 1 or prime No list of one number exhausts earlier than other
			while( i <= nFactorsA){
				distance+=primeFactorExponents1[i];
				minOrdinate++;
				i++;
			}
			while( j <= nFactorsB){
				distance+=primeFactorExponents2[j];
				minOrdinate++;
				j++;
			}
			System.out.println(testCaseCounter+". "+minOrdinate+":"+distance);
			
			s.nextLine();
			a=s.nextInt();
			b=s.nextInt();
			
		}
		

	}

}

