#include<bits/stdc++.h>

using namespace std;

typedef complex<long double> base;

#define pb push_back
#define ll long long
#define ld long double

const int maxn=(int)2e5+5;
ld pi=2.0*acos(0.0);
const int size=100;
int a[size][size];

vector<ld> gauss(const vector< vector<ld> > mx)
{	
	int n=(int)mx.size();
	
	for(int i=0;i<n;i++)
	{	
		memset(a[i],0,sizeof(a[i]));
		
		for(int j=0;j<n+1;j++)
		{
			a[i][j]=mx[i][j];
		}
	}
	
	for(int i=0;i<n;i++)
	{	
		int pos=i;
		
		for(int j=i+1;j<n;j++)
		{
			if(fabs(a[j][i])>fabs(a[pos][i]))
			{
				pos=j;
			}
		}
		
		for(int j=0;j<n+1;j++)
		{
			ld temp=a[i][j];a[i][j]=a[pos][j];
			
			a[pos][j]=temp;
		}
		
		ld now=a[i][i];
		
		for(int j=0;j<n+1;j++)
		{
			a[i][j]/=now;
		}
		
		for(int j=0;j<n;j++)
		{
			if(j!=i)
			{
				ld now=a[j][i];
				
				for(int k=0;k<n+1;k++)
				{
					a[j][k]-=(a[i][k]*now);
				}
			}
		}	
	}
	
	vector<ld> ret;
	
	for(int i=0;i<n;i++)
	{	
		ret.pb(a[i][n]);
	}
	
	return ret;
}


int main()
{
	return 0;
}



