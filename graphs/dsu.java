import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class dsu
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static int[] parent,size;
	
	static int getParent(int u)
	{
		if(u==parent[u])
		{
			return u;
		}
		
		else
		{
			int val=getParent(parent[u]);parent[u]=val;
			
			return val;
		}
	}
	
	static void merge(int u,int v)
	{
		int x=getParent(u),y=getParent(v);
		
		if(x!=y)
		{
			parent[y]=x;
			
			size[x]+=size[y];size[y]=0;
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