import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class transform_of_other_type
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static long mod=(long)(1e9+7);
	
	static int add(long a,long b)
	{
		long ret=a+b;
		
		if(ret>=mod)
		{
			ret%=mod;
		}
		
		return (int)ret;
	}
	
	static int mul(long a,long b)
	{
		long ret=a*b;
		
		if(ret>=mod)
		{
			ret%=mod;
		}
		
		return (int)ret;
	}
	
	static int pow(long a,long b)
	{
		long x=1,y=a;
		
		while(b>0)
		{	
			if(b%2==1)
			{
				x=mul(x,y);
			}
			
			y=mul(y,y);b=b/2;
		}
		
		return (int)x;
	}
	
	static int[] t_xor(int[] a,boolean inv)
	{
		int n=a.length; // compulsary power of 2 ...

		for(int len=2;len<=n;len*=2)
		{
			int half=len/2;
			
			for(int i=0;i<n;i+=len)
			{
				for(int j=0;j<half;j++)
				{
					int u=a[i+j],v=a[i+half+j];
					
					a[i+j]=add(u,v);
						
					a[i+j+half]=add(u,add(-v,mod));
				}
			}
		}
		
		if(inv)
		{
			int invd=pow(n,mod-2);
			
			for(int i=0;i<n;i++)
			{
				a[i]=mul(a[i],invd);
			}
		}
		
		return a;
	}
	
	static int[] t_and(int[] a,boolean inv)
	{
		int n=a.length; // compulsary power of 2 ...

		for(int len=2;len<=n;len*=2)
		{
			int half=len/2;
			
			for(int i=0;i<n;i+=len)
			{
				for(int j=0;j<half;j++)
				{
					int u=a[i+j],v=a[i+half+j];
					
					if(!inv)
					{
						a[i+j]=v;
						
						a[i+j+half]=add(u,v);
					}
					
					else
					{
						a[i+j]=add(add(-u,mod),v);
						
						a[i+half+j]=u;
					}
				}
			}
		}
		
		return a;
	}
	
	static int[] t_or(int[] a,boolean inv)
	{
		int n=a.length;
		
		for(int len=2;len<=n;len*=2)
		{
			int half=len/2;
			
			for(int i=0;i<n;i+=len)
			{
				for(int j=0;j<half;j++)
				{
					int u=a[i+j],v=a[i+half+j];
					
					if(!inv)
					{
						a[i+j]=add(u,v);
						
						a[i+j+half]=u;
					}
					else
					{
						a[i+j]=v;
						
						a[i+j+half]=add(u,add(-v,mod));
					}
				}
			}
		}
	
		return a;
	}
	
	static int[] and_conv(int[] a,int[] b)
	{
		int n=a.length;// length of both is same, and is compulsary power of 2...
		
		a=t_and(a,false);b=t_and(b,false);
		
		for(int i=0;i<n;i++)
		{
			a[i]=mul(a[i],b[i]);
		}
		
		a=t_and(a,true);
		
		return a;
	}
	
	static int[] or_conv(int[] a,int[] b)
	{
		int n=a.length;// length of both is same, and is compulsary power of 2...
		
		a=t_or(a,false);b=t_or(b,false);
		
		for(int i=0;i<n;i++)
		{
			a[i]=mul(a[i],b[i]);
		}
		
		a=t_or(a,true);
		
		return a;
	}
	
	static int[] xor_conv(int[] a,int[] b)
	{
		int n=a.length;// length of both is same, and is compulsary power of 2...
		
		a=t_xor(a,false);b=t_xor(b,false);
		
		for(int i=0;i<n;i++)
		{
			a[i]=mul(a[i],b[i]);
		}
		
		a=t_xor(a,true);
		
		return a;
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