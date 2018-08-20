import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class segment_tree
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static long[] lazy,tree;
	
	static void build(int node,int s,int e)
	{
		if(s>e)
		{
			return;
		}
		
		if(s==e)
		{
			tree[node]=10;
		}
		
		else
		{
			int mid=(s+e)>>1;
			
			build(node<<1,s,mid);build(node<<1|1,mid+1,e);
			
			tree[node]=merge(tree[node<<1],tree[node<<1|1]);
		}
	}
	
	static long merge(long left,long right)
	{
		return Math.max(left,right);
	}
	
	static void point_update(int node,int s,int e,int l,int r,int val)
	{
		if(s>e || l>e || r<s)	
		{
			return;
		}
		
		if(s==e)
		{
			tree[node]=val;
		}
		
		else
		{
			int mid=(s+e)>>1;
			
			point_update(node<<1,s,mid,l,r,val);point_update(node<<1|1,mid+1,e,l,r,val);
			
			tree[node]=merge(tree[node<<1],tree[node<<1|1]);
		}
	}
	
	static void range_update(int node,int s,int e,int l,int r,int val)
	{
		if(lazy[node]!=0)
		{
			tree[node]=Math.max(tree[node],lazy[node]);
			
			if(s!=e)
			{
				lazy[node<<1]=Math.max(lazy[node],lazy[node<<1]);
				
				lazy[node<<1|1]=Math.max(lazy[node],lazy[node<<1|1]);
			}
			
			lazy[node]=0;
		}
		
		if(s>e || l>e || r<s)
		{
			return;
		}
		
		if(l<=s && r>=e)
		{
			tree[node]=Math.max(tree[node],val);
			
			if(s!=e)
			{
				lazy[node<<1]=Math.max(lazy[node<<1],val);lazy[node<<1|1]=Math.max(lazy[node<<1|1],val);
			}
		}
		
		else
		{
			int mid=(s+e)>>1;
			
			range_update(node<<1,s,mid,l,r,val);range_update(node<<1|1,mid+1,e,l,r,val);
			
			tree[node]=merge(tree[node<<1],tree[node<<1|1]);
		}
	}
	
	static long query(int node,int s,int e,int l,int r)
	{
		if(s>e || l>e || r<s)
		{
			return 0;
		}
		
		if(l<=s && r>=e)
		{
			return tree[node];
		}
		
		else
		{
			int mid=(s+e)>>1;
			
			return merge(query(node<<1,s,mid,l,r),query(node<<1|1,mid+1,e,l,r));
		}
	}
	
	static long lazy_query(int node,int s,int e,int l,int r)
	{
		if(lazy[node]!=0)
		{
			tree[node]=Math.max(tree[node],lazy[node]);
			
			if(s!=e)
			{
				lazy[node<<1]=Math.max(lazy[node],lazy[node<<1]);
				
				lazy[node<<1|1]=Math.max(lazy[node],lazy[node<<1|1]);
			}
			
			lazy[node]=0;
		}
		
		if(s>e || l>e || r<s)
		{
			return 0;
		}
		
		if(l<=s && r>=e)
		{
			return tree[node];
		}
		
		else
		{
			int mid=(s+e)>>1;
			
			return merge(lazy_query(node<<1,s,mid,l,r),lazy_query(node<<1|1,mid+1,e,l,r));
		}
	}
	
    public static void main(String args[]) throws Exception
    {
		int n=sc.nextInt();tree=new long[4*n];lazy=new long[4*n];
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