
//spoj yoda
//problem based on calculating Binomial Coefficients using dp to avoid overflows
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class YODA {
	
	private static int count[]= new int['z'-'a'+1];

	//calculate binomial coefficients using DP
	static long calculateBinomialCoefficient(int n,int r){
		
		
		long[][] coefficients = new long[n+1][r+1];
		//satisfy preconditions 
		for(int i=0;i<=n;i++){
			coefficients[i][0]=1;
			if(i<=r)
				coefficients[i][i]=1;
		}
		for(int i=1;i<=r;i++)
			coefficients[0][i]=0;
		
		for(int i=1;i<=n;i++)
			for(int j=1;j<=r;j++)
				coefficients[i][j]=coefficients[i-1][j]+coefficients[i-1][j-1];
		
		return coefficients[n][r];
		
	}
	
	private static long fac(long k){
		long result =1;
		for(long i=2;i<=k;i++)
			result*=i;
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader rdr = new BufferedReader( new InputStreamReader(System.in));
		
			for(String input=rdr.readLine(); input!=null; input=rdr.readLine()){
				Arrays.fill(count, 0);
				String myInput = input.toLowerCase();
				for(int i=0;i<myInput.length();i++){
					char c = myInput.charAt(i);
					if(c >= 'a' && c <='z')
						count[c-'a']++;
				}
				
				
				
				int single=0;
				int upper=0;
				int c=0;
				ArrayList<Integer> same = new ArrayList<Integer>();
				for(int i=0;i<count.length;i++){
					if(count[i]>0 ){
						c+=count[i];
						if(count[i]%2==1){
							single++;
						}
						upper+=(count[i]/2);
						if(count[i]/2 > 1)
							same.add(count[i]/2);
					}
				}
				

				if(single>1 ){
					System.out.println("0");
					continue;
				}
				if(c==0){
					System.out.println("1");
					continue;
				}
				
				Collections.sort(same,Collections.reverseOrder());
				long output =1;
				int n= upper;
				//use Binomial coefficients to find factorials to avoid overflow.
				for(Integer i: same){
					output = output * calculateBinomialCoefficient(n, i);
					n=n-i;
				}
				output = output * fac(n);
			
			System.out.println(output);
	}
	}

}

