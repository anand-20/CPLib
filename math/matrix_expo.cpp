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

const int maxn=15;
const ll mod=(ll)(1e9+7);
ld pi=2.0*acos(0.0);
int a[maxn];

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

static int poww(ll a,ll b)
{
    ll x=1,y=a;

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

vector< vector<int> > matrix_mul(vector< vector<int> > a,vector< vector<int> > b,int r1,int c1,int r2,int c2)
{
    if(r2!=c1)
    {
        assert(false);
    }

    vector< vector<int> > c(r1);

    for(int i=0;i<r1;i++)
    {
        c[i].resize(c2);
    }

    for(int i=0;i<r1;i++)
    {
        for(int j=0;j<c2;j++)
        {
            for(int k=0;k<c1;k++)
            {
                c[i][j]=add(c[i][j],mul(a[i][k],b[k][j]));
            }
        }
    }

    return c;
}

vector< vector<int> > id(int n)
{
    vector< vector<int> > ret(n);

    for(int i=0;i<n;i++)
    {
        ret[i].resize(n);

        ret[i][i]=1;
    }

    return ret;
}

vector< vector<int> > matrix_pow(vector< vector<int> > a,int n,int b)
{
    vector< vector<int> > x=id(n),y=a;

    while(b>0)
    {
        if(b%2==1)
        {
            x=matrix_mul(x,y,n,n,n,n);
        }

        y=matrix_mul(y,y,n,n,n,n);b/=2;
    }

    return x;
}

int main()
{

    
    return 0;
}
