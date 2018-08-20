import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class sparse_table
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static int[] log;
	static int[][] st;
	static int maxn=(int)(2e5+5);
	static int[] a;
	static int LN=21;
	
	static int query(int l,int r)
	{
		int val=log[r-l];
		
		int x=st[val][l],y=st[val][r-(1<<val)+1];
		
		return (a[x]<=a[y]?x:y);
	}
	
	static void sparse_table_array()
	{
		int n=a.length;
		
		for(int i=2;i<maxn;i++)
		{
			log[i]=log[i/2]+1;
		}
		
		for(int i=0;i<n;i++)
		{
			st[0][i]=i;
		}
		
		for(int i=1;i<LN;i++)
		{
			for(int j=0;(1<<i)+j<=n;j++)
			{
				int x=st[i-1][j],y=st[i-1][j+(1<<(i-1))];
				
				st[i][j]=(a[x]<=a[y])?x:y;
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