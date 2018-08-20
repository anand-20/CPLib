#include<bits/stdc++.h>

using namespace std;

typedef complex<long double> base;
typedef long double ld;
typedef long long ll;

#define pb push_back

const int maxn=(int)2e5+5;
const ll mod=(ll)(1e9+7);
ld pi=2.0*acos(0.0);

int mul(ll a,ll b)
{
	ll ret=(a*b);
	
	if(ret>=mod)
	{
		ret%=mod;
	}
	
	return (int)ret;
}

int add(ll a,ll b)
{
	ll ret=a+b;
	
	if(ret>=mod)
	{
		ret%=mod;
	}
	
	return (int)ret;
}

int sub(ll a,ll b)
{
	ll ret=a-b;
	
	if(ret<0)
	{
		ret+=mod;
	}
	
	return (int)ret;
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
		ld ang=(2.0*pi/len)*(invert?-1:1);
			
		base mul_factor(cos(ang),sin(ang));
		
		for(int i=0;i<n;i+=len)
		{
			base now(1,0);
			
			for(int j=0;j<len/2;j++)
			{
				base u=a[i+j],v=a[i+j+len/2]*now;
				
				a[i+j]=u+v;;a[i+j+len/2]=u-v;
				
				now*=mul_factor;
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
	
	vector<int> ret;
	
	for(int i=0;i<size;i++)
	{
		ll a0b0=(ll)(x[i].real()+0.5)%mod,a1b0=(ll)(y[i].real()+0.5)%mod,a1b1=(ll)(z[i].real()+0.5)%mod;
		
		int square=mul(sq,sq);
		
		ret.pb(add(mul(square,a0b0),add(mul(sq,a1b0),a1b1)));
	}
	
	while(ret.back()==0)
	{
		ret.pop_back();
	}
	
	return ret;
}

int main()
{
	return 0;
}



