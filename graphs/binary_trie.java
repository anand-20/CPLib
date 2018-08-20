import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class binary_trie
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static int LN=30;
	static int maxn=(int)(LN*(1e5));
	static int[][] trie=new int[2][maxn];
	static int[] arr=new int[maxn];
	static int ctr=0;
	
	static void insert(int num)
	{
		int root=0;
		
		for(int i=LN-1;i>=0;i--)
		{
			int bit=0;
			
			if((num&(1<<i))!=0)
			{
				bit=1;
			}
			
			int next=trie[bit][root];
			
			if(next==-1)
			{
				trie[bit][root]=next=++ctr;
			}
			
			root=next;arr[root]++;
		}
	}
	
	static int query(int curr,int k)
	{
		// find the number of elements 'i' in trie, such that curr^i<k...
		
		int ret=0,root=0;
		
		for(int i=LN-1;i>=0;i--)
		{
			if(root==-1)
			{
				return ret;
			}
			
			int bit1=0,bit2=0;
			
			if((k&(1<<i))!=0)
			{
				bit1=1;	
			}
			
			if((curr&(1<<i))!=0)
			{
				bit2=1;
			}
			
			if(bit1==1)
			{
				ret+=get(trie[bit2][root]);
				
				int next=trie[1-bit2][root];
				
				root=next;
			}
			
			else
			{
				int next=trie[bit2][root];
				
				root=next;
			}
		}
		
		return ret;
	}
	
	static int get(int node)
	{
		return (node!=-1?arr[node]:0);
	}
	
    public static void main(String args[]) throws Exception
    {
		for(int i=0;i<2;i++)
		{
			Arrays.fill(trie[i],-1);
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