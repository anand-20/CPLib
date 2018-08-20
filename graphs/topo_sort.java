import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class topo_sort
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	
	static List<Integer> topo_sort(ArrayList<Integer>[] al,int n)
	{
		int[] deg=new int[n];
		
		ArrayDeque<Integer> ad=new ArrayDeque<>();
		
		for(int i=0;i<n;i++)
		{	
			for(int x:al[i])
			{	
				deg[x]++;
			}
		}
		
		for(int i=0;i<n;i++)
		{
			if(deg[i]==0)
			{
				ad.add(i);
			}
		}
		
		List<Integer> list=new ArrayList<>();
		
		while(ad.size()>0)
		{
			int curr=ad.removeFirst();
			
			list.add(curr);
			
			for(int x:al[curr])
			{
				deg[x]--;
				
				if(deg[x]==0)
				{
					ad.add(x);
				}
			}
		}
		
		return list;
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