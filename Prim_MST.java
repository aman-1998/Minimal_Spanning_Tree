import java.util.*;
import java.lang.*;

class MST
{
	int Prim(int[][] cost,int n,int min,int k,int l)
	{
		int min_cost=min,i,j;
		int[][] t=new int[n-1][2];
		t[0][0]=k;
		t[0][1]=l;
		int[] near=new int[n+1];
		for(i=1;i<=n;i++)
		{
			if(cost[i][k]<cost[i][l])
				near[i]=k;
			else
				near[i]=l;
		}
		near[k]=0;
		near[l]=0;
		for(i=1;i<=n-2;i++)
		{
			min=999;
			for(j=1;j<=n;j++)
			{
				if(near[j]!=0)
				{
					if(cost[j][near[j]]<min)
					{
						min=cost[j][near[j]];
						k=j;
						l=near[j];
					}
				}
			}
			min_cost=min_cost+min;
			near[k]=0;
			t[i][0]=k;
			t[i][1]=l;
			for(j=1;j<=n;j++)
			{
				if(near[j]!=0)
				{
					if(cost[j][k]<cost[j][near[j]])
						near[j]=k;
				}
			}
		}
		System.out.println("\nEdges:- ");
		for(i=0;i<=n-2;i++)
			System.out.println(t[i][0]+" - "+t[i][1]);
		return min_cost;
	}
	public static void main(String args[])
	{
		int i,j,k=0,l=0;
		Scanner in=new Scanner(System.in);
		System.out.print("Enter no. of vertices: ");
		int n=in.nextInt();
		int[][] cost=new int[n+1][n+1];
		int min=999;
		for(i=1;i<=n;i++)
		{
			for(j=1;j<=n;j++)
			{
				System.out.print("Enter cost["+i+"]["+j+"] : ");
				cost[i][j]=in.nextInt();
				if(cost[i][j]<min && cost[i][j]>0)
				{
					min=cost[i][j];
					k=i;
					l=j;
				}
			}
		}
		System.out.print("Weight Matrix: \n");
		for(i=1;i<=n;i++)
		{
			for(j=1;j<=n;j++)
			{
				if(cost[i][j]==999)
					System.out.print((char)8734+" ");
				else
					System.out.print(cost[i][j]+" ");
			}
			System.out.println();
		}
		MST x=new MST();
		int min_cost=x.Prim(cost,n,min,k,l);
		System.out.println("Min cost = "+min_cost);
	}
}