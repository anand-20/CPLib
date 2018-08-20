//#pragma GCC optimize("Ofast,no-stack-protector")
//#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx")
//#pragma GCC target("avx,tune=native")
// Anand Jaisingh

#include<bits/stdc++.h>

using namespace std;

typedef complex<double> base;
typedef long double ld;
typedef long long ll;

#define pb push_back
#define pii pair<int,int>
#define pll pair< ll , ll >
#define vi vector<int>
#define vvi vector< vi >

const int LN=18,maxn=1<<LN;
const ll mod=(ll)(998244353);
int a[maxn],poww_r[maxn],poww_inv_r[maxn];

// a good fast version of NTT, modulo 998244353...

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

const int r=3,inv_r=poww(r,mod-2);

void pre()
{
    int zz=poww(r,(mod-1)>>LN),curr=1;

    for(int i=0;i<maxn;i++)
    {
        poww_r[i]=curr;

        curr=(curr*zz)%mod;
    }

    for(int i=0;i<maxn;i++)
    {
        poww_inv_r[i]=(i==0)?1:poww_r[maxn-i];
    }
}

void fft(vi& a, bool invert)
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
        int additive=maxn/len;

        for(int i=0;i<n;i+=len)
        {
            int now=0;

            for(int j=0;j<len/2;j++)
            {
                int u=a[i+j],v=mul(a[i+j+len/2],invert?poww_inv_r[now]:poww_r[now]);

                a[i+j]=add(u,v);a[i+j+len/2]=add(u,mod-v);

                now+=additive;
            }
        }
    }
}

vi mult(vi &a,vi &b)
{
    int size=1;
	
	while(size<max(a.size(),b.size()))
	{
		size*=2;
	}
	
	size*=2;

    vi fa(size),fb(size);

    for(int i=0;i<a.size();i++)
    {
        fa[i]=a[i];
    }

    for(int i=0;i<b.size();i++)
    {
        fb[i]=b[i];
    }

    fft(fa,false);fft(fb,false);

    for(int i=0;i<size;i++)
    {
        fa[i]=mul(fa[i],fb[i]);
    }

    fft(fa,true);int inv_sz=poww(size,mod-2);

    for(int i=0;i<size;i++)
    {
        fa[i]=mul(fa[i],inv_sz);
    }

    while(fa.back()==0)
    {
        fa.pop_back();
    }

    return fa;
}

int main()
{
    ios_base::sync_with_stdio(0);

	pre();

    return 0;
}
