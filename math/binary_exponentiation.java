import java.io.*;
import java.util.*;
public final class binary_exponentiation
{
    static FastScanner sc=new FastScanner(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out=new PrintWriter(System.out);
	static long mod=(long)(1e9+7);
	
	static long pow(long a,long b)
	{
		long x=1,y=a;
		
		while(b>0)
		{
			if(b%2==1)
			{
				x=(x*y)%mod;
			}
			
			y=(y*y)%mod;b=b/2;
		}
		
		return x;
	}
	
	public static void main(String args[]) throws Exception
	{
		
		out.close();
	}
}
class FastScanner
{
    BufferedReader in;
    StringTokenizer st;

    public FastScanner(BufferedReader in) {
        this.in = in;
    }
	
    public String nextToken() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine());
        }
        return st.nextToken();
    }
	
	public String next() throws Exception {
		return nextToken().toString();
	}
	
    public int nextInt() throws Exception {
        return Integer.parseInt(nextToken());
    }

    public long nextLong() throws Exception {
        return Long.parseLong(nextToken());
    }

    public double nextDouble() throws Exception {
        return Double.parseDouble(nextToken());
    }
}