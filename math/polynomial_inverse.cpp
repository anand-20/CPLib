//#pragma GCC optimize("Ofast,no-stack-protector")
//#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx")
//#pragma GCC target("avx,tune=native")


#include<bits/stdc++.h>

using namespace std;

typedef complex<long double> base;
typedef long double ld;
typedef long long ll;

#define pb push_back

const int LN=18,maxn=1<<LN;
const ll mod=(ll)(1e9+7);
ld pi=2.0*acos(0.0);
base up[maxn],down[maxn];
int b[maxn],res[maxn],s[maxn],e[maxn];

inline int mul(ll a,ll b)
{
	return (a*b)%mod;
}
     
inline int add(int a,int b)
{
	int ret=a+b;
        
	if(ret>=mod)
	{
		ret-=mod;
	}
        
	return ret;
}
     
inline int sub(int a,int b)
{
	int ret=a-b;
        
	if(ret<0)
	{
		ret+=mod;
	}
        
	return ret;
}
     
inline int poww(ll a,ll b)
{
	int x=1,y=a;
     
	while(b>0)
	{
		if(b%2)
		{
			x=mul(x,y);
		}
     
		y=mul(y,y);b/=2;
	}
     
	return (int)(x%mod);
}

void pre()
{
	for(int i=0;i<maxn/2;i++)
	{
		ld ang=(2.0*pi*i)/maxn;
		
		up[i]=base(cos(ang),sin(ang));
		
		down[i]=base(cos(ang),sin(-ang));
	}
}

vector<int> mult_naive(vector<int> &a,vector<int> &b)
{
	vector<int> ret((int)(a.size()+b.size()-1));
	
	for(int i=0;i<(int)a.size();i++)
	{
		for(int j=0;j<(int)b.size();j++)
		{
			ret[i+j]=add(ret[i+j],mul(a[i],b[j]));
		}
	}
	
	return ret;
}

void fft(vector<base> &a,bool invert)
{
	int n=a.size();
	
	for(int i=1,j=0;i<n;i++)
	{
		int bit=n>>1;
		
		for(;j>=bit;bit>>=1)
		{
			j-=bit;
		}
		
		j+=bit;
		
		if(i<j) 
		{
			swap(a[i],a[j]);
		}
	}
 
	for(int len=2;len<=n;len<<=1)
	{
		for(int i=0;i<n;i+=len)
		{
			int idx=0,add=maxn/len;
			
			for(int j=0;j<len/2;j++)
			{
				base u=a[i+j],v=a[i+j+len/2]*(invert?down[idx]:up[idx]);
				
				a[i+j]=u+v;a[i+j+len/2]=u-v;
				
				idx+=add;
			}
		}
	}
	
	if(invert)
	{
		for(int i=0;i<n;i++)
		{
			a[i]/=base(n,0);
		}
	}
}

vector<int> mult(vector<int> &a,vector<int> &b)
{
	int size=1;
	
	while(size<max(a.size(),b.size()))
	{
		size*=2;
	}
	
	size*=2;
	
	vector<base> fa(size),fb(size);
	
	int sq=(int)sqrt(mod)+10;
	
	for(int i=0;i<a.size();i++)
	{
		fa[i]=(base(a[i]/sq,a[i]%sq));
	}
	
	for(int i=0;i<b.size();i++)
	{
		fb[i]=(base(b[i]/sq,b[i]%sq));
	}
	
	fft(fa,0);fft(fb,0);vector<base> x(size),y(size),z(size);
	
	// i have used 5 fft's , not 4...
	
	for(int i=0;i<size;i++)
	{
		base q1=fa[i]+conj(fa[(size-i)%size]),q2=fa[i]-conj(fa[(size-i)%size]);
		
		base a_0=(q1/base(2,0)),a_1=q2/base(0,2);
		
		q1=fb[i]+conj(fb[(size-i)%size]);q2=fb[i]-conj(fb[(size-i)%size]);
		
		base b_0=(q1/base(2,0)),b_1=q2/base(0,2);
		
		x[i]=a_0*b_0;
		
		y[i]=a_1*b_0+a_0*b_1;
		
		z[i]=a_1*b_1;
	}
	
	fft(x,1);fft(y,1);fft(z,1);
	
	vector<int> c(size);
	
	for(int i=0;i<size;i++)
	{
		ll a0b0=(ll)(x[i].real()+0.5)%mod,a1b0=(ll)(y[i].real()+0.5)%mod,a1b1=(ll)(z[i].real()+0.5)%mod;
		
		int square=mul(sq,sq);
		
		c[i]=add(mul(square,a0b0),add(mul(sq,a1b0),a1b1));
	}
	
	while(c.back()==0)
	{
		c.pop_back();
	}
	
	return c;
}

vector<int> convolve(int s,int e,int l,int r)
{
	vector<int> x,y;
	
	for(int i=s;i<=e;i++)
	{
		x.pb(res[i]);
	}
	
	for(int i=l;i<=r;i++)
	{
		y.pb(b[i]);
	}
	
	return mult(x,y);
}

void inv(int n)
{
	res[0]=poww(b[0],mod-2);
	
	res[1]=mul(res[0],b[1]);res[2]=mul(res[0],b[2]);
	
	int zz=sub(mod,res[0]);
	
	for(int i=3,j=2;i<maxn;i+=j,j+=j)
	{
		s[j]=i;e[j]=i+j-1;
	}
	
	for(int i=1;i<=n;i++)
	{
		res[i]=mul(zz,res[i]);
		
		res[i+1]=add(res[i+1],mul(res[i],b[1]));res[i+2]=add(res[i+2],mul(res[i],b[2]));
		
		int now=2;
		
		while(i%now==0)
		{
			vector<int> zz=convolve(i-now,i-1,s[now],e[now]);
			
			for(int j=0;j<(int)zz.size();j++)
			{
				int pos=i-now+s[now];
				
				//printf("%d\n",pos+j);
				
				res[pos+j]=add(res[pos+j],zz[j]);
			}
			
			now*=2;
		}
	}
}

int main()
{
	ios_base::sync_with_stdio(0);
	
	pre();
	
	return 0;
}



