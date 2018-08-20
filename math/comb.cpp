#include<bits/stdc++.h>

using namespace std;

typedef complex<long double> base;
typedef long double ld;
typedef long long ll;

#define pb push_back

const int maxn=(int)2e5+5;
int fact[maxn],inv_fact[maxn];
const ll mod=(ll)(1e9+7);
ld pi=2.0*acos(0.0);

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

void build()
{
	fact[0]=1;
	
	for(int i=1;i<maxn;i++)
	{
		fact[i]=mul(fact[i-1],i);
	}
	
	inv_fact[maxn-1]=poww(fact[maxn-1],mod-2);
	
	for(int i=maxn-2;i>=0;i--)
	{
		inv_fact[i]=mul(inv_fact[i+1],i+1);
	}
}

int C(int n,int r)
{
	if(n<r || min(n,r)<0)
	{
		return 0;
	}
	
	int val1=fact[n],val2=mul(inv_fact[r],inv_fact[n-r]);
	
	return mul(val1,val2);
}

int main()
{
	build();
	
	return 0;
}



