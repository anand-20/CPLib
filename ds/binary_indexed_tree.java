import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class binary_indexed_tree_1
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static long[] bit;
	static int n;
	
	static void update_prefix(int idx,long val)
	{
		while(idx<=n)
		{
			bit[idx]+=val;idx+=idx&-idx;
		}
	}
	
	static long query_prefix(int idx,long val)
	{
		long ret=0;
		
		while(idx>0)
		{
			ret=ret+bit[idx];idx-=idx&-idx;
		}
		
		return ret;
	}
	
	static void update_suffix(int idx,long val)
	{
		while(idx>0)
		{
			bit[idx]+=val;idx-=idx&-idx;
		}
	}
	
	static long query_suffix(int idx,long val)
	{
		long ret=0;
		
		while(idx<=n)
		{
			ret=ret+bit[idx];idx+=idx&-idx;
		}
		
		return ret;
	}
	
    public static void main(String args[]) throws Exception
    {
		n=sc.nextInt();bit=new long[n+1];
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