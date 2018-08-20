import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class segment_tree_vector
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static int[][] tree;
	
	static void build(int node,int s,int e)
	{
		if(s>e)
		{
			return;
		}
		
		if(s==e)
		{	
			tree[node]=new int[]{10};
		}
		
		else
		{
			int mid=(s+e)>>1;
			
			build(node<<1,s,mid);build(node<<1|1,mid+1,e);
			
			tree[node]=merge(tree[node<<1],tree[node<<1|1]);
		}
	}
	
	// merge function goes here...
	
	static int[] merge(int[] left,int[] right)
	{
		int n=left.length+right.length;int[] ret=new int[n];
		
		int ptr=0,i=0,j=0;
		
		while(i<left.length || j<right.length)
		{
			if(i==left.length)
			{
				ret[ptr++]=right[j++];
			}
			
			else if(j==right.length)
			{	
				ret[ptr++]=left[i++];
			}
			
			else if(left[i]<=right[j])
			{
				ret[ptr++]=left[i++];
			}
			
			else
			{
				ret[ptr++]=right[j++];
			}
		}
		
		return ret;
	}
	
	// search function goes here...
	
	static int searchLast(int[] arr,int val)
	{
		int low=0,high=arr.length-1;
		
		while(low<high)
		{
			int mid=(low+high+1)>>1;
			
			if(arr[mid]<=val)
			{
				low=mid;
			}
			else
			{
				high=mid-1;
			}
		}
		
		return (arr.length>0 && arr[low]<=val?arr[low]:-1);
	}
	
	
	// query function is here...
	
	static int query(int node,int s,int e,int l,int r,int val)
	{
		if(s>e || l>e || r<s)
		{
			return Integer.MAX_VALUE;
		}
		
		if(l<=s && r>=e)
		{
			return searchLast(tree[node],val);
		}
		
		else
		{
			int mid=(s+e)>>1;
			
			return Math.min(query(node<<1,s,mid,l,r,val),query(node<<1|1,mid+1,e,l,r,val));
		}
	}
	
    public static void main(String args[]) throws Exception
    {
		int n=sc.nextInt();tree=new int[n][];
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