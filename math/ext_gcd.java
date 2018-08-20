import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class ext_gcd
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	
	static long[] ext_gcd(long a,long b)
	{
		long r0=a,r1=b,x0=1,y0=0,x1=0,y1=1,q=r0/r1,r;
		
		while((r=(r0-q*r1))>0)	
		{
			long xx=(x0-q*x1),yy=(y0-q*y1);
			
			x0=x1;y0=y1;x1=xx;y1=yy;
			
			r0=r1;r1=r;
			
			q=r0/r1;
		}
		
		return new long[]{r1,x1,y1};
		
		// returning gcd,x,y
	}
	
    public static void main(String args[]) throws Exception
    {
		
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