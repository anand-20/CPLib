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

const int maxn=(int)(2e5+5);
const ll mod=(ll)(998244353);
int a[maxn];

ll eval(ll m,ll x,ll b)
{
    return m*x+b;
}

void dnc(vector< pll > &a,vector< pll > &b,int s,int e,int l,int r)
{
	// a has the lines, b has the x queries...
	
	
	if(l>r)
	{
		return;
	}

	int mid=(l+r)>>1,best=-1;

	ll curr=LONG_LONG_MAX;

	for(int i=s;i<=e;i++)
	{
		ll now=eval(a[i].first,b[mid].second,a[i].second);

		if(now<curr)
		{
			curr=now;best=i;
		}
	}

	res[b[mid].first]=min(res[b[mid].first],curr);

	dnc(a,b,s,best,l,mid-1);dnc(a,b,best,e,mid+1,r);
}

int main()
{
    ios_base::sync_with_stdio(0);

	

    return 0;
}
