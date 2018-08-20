import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class two_sat
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static boolean[] vis;
	static int[] comp;
	static int ctr=0;
	static List<Integer> list;
	
	static void dfs1(int u,ArrayList<Integer>[] al)
	{
		vis[u]=true;
		
		for(int x:al[u])
		{
			if(!vis[x])
			{
				dfs1(x,al);
			}
		}
		
		list.add(u);
	}
	
	static void dfs2(int u,ArrayList<Integer>[] al)
	{
		vis[u]=true;comp[u]=ctr;
		
		for(int x:al[u])
		{
			if(!vis[x])
			{
				dfs2(x,al);
			}
		}
	}
	
	static void scc(ArrayList<Integer>[] al,int n)
	{
		vis=new boolean[n];list=new ArrayList<>();
		
		for(int i=0;i<n;i++)
		{
			if(!vis[i])
			{
				dfs1(i,al);
			}
		}
		
		Arrays.fill(vis,false);
		
		for(int i=list.size()-1;i>=0;i--)
		{
			int x=list.get(i);
			
			if(!vis[x])
			{
				dfs2(x,al);ctr++;
			}
		}
	}
	
	static boolean two_sat(ArrayList<Integer>[] al,int n)
	{
		comp=new int[n];scc(al,n);
		
		for(int i=0;i<n;i++)
		{
			if(comp[i]==comp[i^1])
			{
				return false;
			}
		}
		
		return true;
	}
	
    public static void main(String args[]) throws Exception
    {
		// pass the graph consisting of assertions to the two_sat function ...
		
		
		
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
