import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class centroid_decomposition
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static int[][] al;
	static int[] x,y,cnt,size;
	static boolean[] removed;
	static int ctr;
	
	static void build(int u,int p)
	{
		size[u]=1;ctr++;
		
		for(int x:al[u])
		{
			if(x!=p && !removed[x])
			{
				build(x,u);size[u]+=size[x];
			}
		}
	}
	
	static int findCentroid(int u,int p)
	{
		int max=-1,big_child=-1;
		
		for(int x:al[u])
		{
			if(x!=p && !removed[x])
			{
				if(size[x]>max)
				{
					max=size[x];big_child=x;
				}
			}
		}
		
		if(ctr-size[u]<=ctr/2 && max<=ctr/2)
		{
			return u;
		}
		
		return findCentroid(big_child,u);
	}
	
	static void dfs1(int u,int p)
	{
		// do something useful here...
		
		for(int x:al[u])
		{
			if(x!=p && !removed[x])
			{
				dfs1(x,u);
			}
		}
	}
	
	static void dfs2(int u,int p)
	{
		// do something useful here...
		
		for(int x:al[u])
		{
			if(x!=p && !removed[x])
			{
				dfs2(x,u);
			}
		}
	}
	
	static void dfs3(int u,int p)
	{
		// do something useful here...
		
		for(int x:al[u])
		{
			if(x!=p && !removed[x])
			{
				dfs3(x,u);
			}
		}
	}
	
	static void decompose(int u,int p) throws Exception
	{
		ctr=0;build(u,p);
		
		int centroid=findCentroid(u,p);removed[centroid]=true;
		
		// update centroid value here...
		
		for(int x:al[centroid])
		{
			if(x!=p && !removed[x])
			{	
				// pick up values updated by others...
				
				dfs1(x,centroid);
				
				// update myself..
				
				dfs2(x,centroid);
			}
		}
		
		// de update centroid value..
		
		for(int x:al[centroid])
		{
			if(x!=p && !removed[x])
			{
				// de-update everything..
				
				dfs3(x,centroid);
			}
		}
		
		for(int x:al[centroid])
		{
			if(x!=p && !removed[x])
			{
				decompose(x,centroid);
			}
		}
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