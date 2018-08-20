import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public final class matrix_expo
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static long mod=(long)(1e9+7);
	
	static long[][] matrix_mul(long[][] a,long[][] b,int r1,int c1,int r2,int c2)
	{
		long[][] c=new long[r1][c2];
		
		if(c1!=r2)
		{
			out.println("Wrong Wrong Wrong");
		}

		for(int i=0;i<r1;i++)
		{
			for(int j=0;j<c2;j++)
			{
				for(int k=0;k<c1;k++)
				{
					long curr=(a[i][k]*b[k][j])%mod;

					c[i][j]=(c[i][j]+curr)%mod;
				}
			}
		}

		return c;
	}
	
	static long[][] matrix_pow(long[][] a,long b,int n)
	{
		long[][] x=id(n),y=a;

		while(b>0)
		{
			if(b%2==1)
			{
				x=matrix_mul(x,y,n,n,n,n);
			}

			y=matrix_mul(y,y,n,n,n,n);b=b/2;
		}

		return x;
	}
	
	static long[][] id(int n)
	{
		long[][] ret=new long[n][n];

		for(int i=0;i<n;i++)
		{
			ret[i][i]=1;
		}

		return ret;
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