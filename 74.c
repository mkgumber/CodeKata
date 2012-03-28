
//spoj DIVSUM
//calculating sum of divisors of a given number
//brute force approach which finds all the divisors and caculates sum
//only trick is calculate divisors upto square root of the number
#include<stdio.h>
#include<math.h>
int main()
{
	int cases,no,sqrt_no,i,j;
	long int sum;
	scanf("%d",&cases);
	for(i=0;i<cases;i++)
	{
		sum=0;
		scanf("%d",&no);
		sqrt_no=sqrt(no);

		for(j=1;j<=sqrt_no;j++)
		{
			if(no%j==0)
			{
				sum=sum+j;
				sum=sum+no/j;
			}
		}
		if(no%sqrt_no==0)
		{
			if(no==sqrt_no*sqrt_no)
			{
				sum=sum-sqrt_no;
			}

		}
		printf("%ld\n",sum-no);
	}
	return 0;
}


