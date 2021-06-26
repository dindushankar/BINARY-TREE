//package <missing>;//

public class GlobalMembers
{
	public static void showData(int[][] g, int n)
	{
		System.out.print("\n");
		System.out.print("\t");
		for (int i = 0;i < n;i++)
		{
		   System.out.print((char)(i + 65));
		   System.out.print(" \t");
		}

		for (int i = 0;i < n;i++)
		{
			System.out.print("\n");
			System.out.print("\n");
			System.out.print((char)(i + 65));
			System.out.print(" \t");
			for (int j = 0;j < n;j++)
			{
				if (g[i][j] == DefineConstants.INF)
				{
					System.out.print((char)236);
				}
				else
				{
					System.out.print(g[i][j]);
				}
				System.out.print(" \t");
			}
		}
	}

	public static void getEdgeDistances(int[][] g, int n)
	{
		for (int i = 0;i < n;i++)
		{
		   for (int j = 0;j < n;j++)
		   {
			  if (i == j)
			  {
				g[i][j] = 0;
			  }
			  else
			  {
				g[i][j] = DefineConstants.INF;
			  }
		   }
		}

		System.out.print("Enter edge distances");
		System.out.print(" (enter 0 if there is no direct edge)\n");
		for (int i = 0;i < n;i++)
		{
			for (int j = i + 1;j < n;j++)
			{
				System.out.print("from ");
				System.out.print((char)(i + 65));
				System.out.print(" to ");
				System.out.print((char)(j + 65));
				System.out.print(":");
				g[i][j] = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));
				if (g[i][j] == 0)
				{
				  g[i][j] = DefineConstants.INF;
				}
				g[j][i] = g[i][j];
			}
		}
	}

	public static boolean areSame(int[][] shDist, int[][] temp, int nodes)
	{
		for (int i = 0;i < nodes;i++)
		{
		   for (int j = 0;j < nodes;j++)
		   {
			  if (shDist[i][j] != temp[i][j])
			  {
				return false;
			  }
		   }
		}
		return true;
	}

	public static void findShortestDistances(int[][] g, int nodes, int[][] shDist)
	{
		int[][] temp = new int[100][100];
		System.out.print("\n");
		System.out.print("Original Costs\n------------------");
		showData(g, nodes);

		for (int i = 0;i < nodes;i++)
		{
		   for (int j = i;j < nodes;j++)
		   {
			  if (j == i)
			  {
				shDist[i][j] = 0;
			  }
			  else
			  {
				shDist[i][j] = shDist[j][i] = DefineConstants.INF;
			  }
		   }
		}

		System.out.print("\n");
		System.out.print("\n");
		System.out.print("Initialized Costs\n------------------");
		System.out.print("\n");
		showData(shDist, nodes);

		for (int i = 0;i < nodes;i++)
		{
			for (int j = 0;j < nodes;j++)
			{
				temp[i][j] = g[i][j];
			}
		}

		int t = 1;
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("Cost for t=");
		System.out.print(t);
		System.out.print("\n-------------");
		System.out.print("\n");
		showData(temp, nodes);

		int from;
		int to;
		int d;
		int d1;
		int d2;
		for (t = 2;t <= nodes;t++)
		{
			for (from = 0;from < nodes;from++)
			{
				for (to = 0;to < nodes;to++)
				{
					if (from == to)
					{
					  continue;
					}
					d = temp[from][to];
					for (int via = 0;via < nodes;via++)
					{
						if (via == from || via == to)
						{
							continue;
						}
						d1 = temp[from][via];
						d2 = temp[via][to];
						if (d1 == DefineConstants.INF || d2 == DefineConstants.INF)
						{
							continue;
						}
						d1 += d2;
						if (d1 < d)
						{
						  d = d1;
						}
					}
					  shDist[from][to] = d;
				}
			}
			if (areSame(shDist, temp, nodes))
			{
			  return;
			}

			System.out.print("\n");
			System.out.print("\n");
			System.out.print("Cost for t=");
			System.out.print(t);

class ConsoleInput
{
	private static boolean goodLastRead = false;
	public static boolean lastReadWasGood()
	{
		return goodLastRead;
	}

	public static String readToWhiteSpace(boolean skipLeadingWhiteSpace)
	{
		String input = "";
		char nextChar;
		while (Character.isWhitespace(nextChar = (char)System.in.read()))
		{
			//accumulate leading white space if skipLeadingWhiteSpace is false:
			if (!skipLeadingWhiteSpace)
			{
				input += nextChar;
			}
		}
		//the first non white space character:
		input += nextChar;

		//accumulate characters until white space is reached:
		while (!Character.isWhitespace(nextChar = (char)System.in.read()))
		{
			input += nextChar;
		}

		goodLastRead = input.length() > 0;
		return input;
	}

	public static String scanfRead()
	{
		return scanfRead(null, -1);
	}

	public static String scanfRead(String unwantedSequence)
	{
		return scanfRead(unwantedSequence, -1);
	}

	public static String scanfRead(String unwantedSequence, int maxFieldLength)
	{
		String input = "";

		char nextChar;
		if (unwantedSequence != null)
		{
			nextChar = '\0';
			for (int charIndex = 0; charIndex < unwantedSequence.length(); charIndex++)
			{
				if (Character.isWhitespace(unwantedSequence.charAt(charIndex)))
				{
					//ignore all subsequent white space:
					while (Character.isWhitespace(nextChar = (char)System.in.read()))
					{
					}
				}
				else
				{
					//ensure each character matches the expected character in the sequence:
					nextChar = (char)System.in.read();
					if (nextChar != unwantedSequence.charAt(charIndex))
						return null;
				}
			}

			input = (new Character(nextChar)).toString();
			if (maxFieldLength == 1)
				return input;
		}

		while (!Character.isWhitespace(nextChar = (char)System.in.read()))
		{
	         	input += nextChar;
			if (maxFieldLength == input.length())
				return input;
		}
                          return input;
           }