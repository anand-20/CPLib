import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class convex_hull_trick
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static List<Pair> list=new ArrayList<Pair>();
	
	static boolean check(Pair p1,Pair p2,Pair p3)
	{
		return (inc(p1,p3)<inc(p2,p3));
	}
	
	static void addLine(long m,long c)
	{
		while(list.size()>=2)
		{
			int idx=list.size()-1;
			
			if(check(new Pair(m,c),list.get(idx),list.get(idx-1)))
			{
				list.remove(idx);
			}
			
			else
			{
				break;
			}
		}
		
		list.add(new Pair(m,c));
	}
	
	static long query(long x)
	{
		int low=0,high=list.size()-1;
		
		while(low<high)
		{
			int mid=(low+high+1)>>1;
			
			long now=inc(list.get(mid),list.get(mid-1));
			
			if(x>=now)
			{
				low=mid;
			}
			
			else
			{
				high=mid-1;
			}
		}
		
		long ret=eval(0,x);
		
		if(list.size()>=2)
		{
			ret=Math.max(ret,eval(low,x));
		}
		
		return ret;
	}
	
	static long eval(int idx,long x)
	{
		return list.get(idx).m*x+list.get(idx).c;
	}

	
	static long inc(Pair p1,Pair p2)
	{
		long val1=p1.m-p2.m,val2=p2.c-p1.c;
		
		return (val2/val1)+(val2%val1==0?0:1);
	}
	
    public static void main(String args[]) throws Exception
    {
		// convex hull trick for maxima, when lines are already sorted by slope and added one by one...
    }
	
	private static class Pair
	{
		long m,c;
		
		public Pair(long m,long c)
		{
			this.m=m;this.c=c;
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