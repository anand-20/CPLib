import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class crt
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	
	static long pow(long a,long b,long mod)
	{
		long x=1,y=a%mod;
		
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
	
	static long[] ext_gcd(long a,long b)
	{
		long r0=a,r1=b,x0=1,y0=0,x1=0,y1=1,q=r0/r1,r;
		
		while((r=r0-q*r1)>0)
		{
			long xx=x0-(q*x1),yy=(y0-q*y1);
			
			x0=x1;y0=y1;x1=xx;y1=yy;
			
			r0=r1;r1=r;
			
			q=r0/r1;
		}
		
		return new long[]{r1,x1,y1};
	}
	
	static long add(long a,long b,long mod)
	{
		long ret=(a+b);
		
		if(ret>=mod)
		{
			ret%=mod;
		}
		
		return ret;
	}
	
	static long mul(long a,long b,long mod)
	{
		long ret=(a*b);
		
		if(ret>=mod)
		{
			ret%=mod;
		}
		
		return ret;
	}
	
	static long crt(List<Node> list)
	{
		int n=list.size();
		
		long[] a=new long[n],m=new long[n];
		
		for(int i=0;i<n;i++)
		{
			a[i]=list.get(i).remainder;
			
			m[i]=list.get(i).mod;
		}
		
		for(int i=1;i<n;i++)
		{
			long[] arr=ext_gcd(m[i-1],m[i]);
			
			long mod=m[i]*m[i-1];
			
			while(arr[1]<0)
			{
				arr[1]+=mod;
			}
			
			while(arr[2]<0)
			{
				arr[2]+=mod;
			}
			
			long val1=mul(a[i],mul(m[i-1],arr[1],mod),mod),val2=mul(a[i-1],mul(m[i],arr[2],mod),mod);
			
			long res=add(val1,val2,mod);
			
			a[i]=res;m[i]=mod;
		}
		
		return a[n-1];
	}
	
    public static void main(String args[]) throws Exception
    {
		
    }
	
	private static class Node
	{
		long remainder,mod;
		
		public Node(long remainder,long mod)
		{
			this.remainder=remainder;
			
			this.mod=mod;
		}
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