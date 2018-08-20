import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class number_theory
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static int[][] divs;
	static int[] cnt,sp;
	static int[] mu;
	static int maxn=(int)(1e3+1);
	static int max=0;
	
	static int gcd(int a,int b)
	{
		if(b==0)
		{
			return a;
		}
		
		else
		{
			return gcd(b,a%b);
		}
	}
	
	static void build()
	{
		divs=new int[maxn][];cnt=new int[maxn];
		
		for(int i=1;i<maxn;i++)
		{
			for(int j=i;j<maxn;j+=i)
			{
				cnt[j]++;
				
				max=Math.max(max,cnt[j]);
			}
		}
		
		for(int i=1;i<maxn;i++)
		{
			divs[i]=new int[cnt[i]];cnt[i]=0;
		}
		
		for(int i=1;i<maxn;i++)
		{
			for(int j=i;j<maxn;j+=i)
			{
				divs[j][cnt[j]++]=i;
			}
		}
		
		sp=new int[maxn];sp[1]=1;
		
		for(int i=2;i<maxn;i++)
		{
			if(sp[i]==0)
			{
				for(int j=i;j<maxn;j+=i)
				{
					sp[j]=i;
				}
			}
		}
	}
	
	static long totient(long m)
	{
		long ret=1;
		
		for(long i=2;i*i<=m;i++)
		{
			int ctr=0;long curr=1;
			
			while(m%i==0)
			{
				m/=i;ctr++;
				
				curr*=i;
			}
			
			if(ctr>0)
			{
				ret*=(curr-(curr/i));
			}
		}
		
		if(m>1)
		{
			ret*=(m-1);
		}
		
		return ret;
	}
	
	static void mobius()
	{
		mu=new int[maxn];Arrays.fill(mu,1);
		
		for(int i=2;i<maxn;i++)
		{
			long curr=(i*1L*i);
			
			if(curr<maxn)
			{
				int now=(int)curr;
				
				for(int j=now;j<maxn;j+=now)
				{
					mu[j]=0;
				}
			}
			
			if(sp[i]==i)
			{
				for(int j=i;j<maxn;j+=i)
				{
					mu[j]*=(-1);
				}
			}
		}
	}
	
    public static void main(String args[]) throws Exception
    {
		build();mobius();Set<Integer> s1=new HashSet<>();
		
		for(int i=1;i<maxn;i++)
		{
			s1.add(divs[i].length);
		}
			
		out.println(s1.size());out.close();
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