public class PercolationStats 
{
	double[] experimentsValue;
	int dimension;
	int iteration;
	private double mean;
	private double stddev;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T)
	{
		dimension = N;
		iteration = T;
	}
	
	// sample mean of percolation threshold
	public double mean() 
	{
		mean = StdStats.mean(experimentsValue);
		return mean;
	}
	
	// sample standard deviation of percolation threshold
	public double stddev()
	{
		stddev = StdStats.stddev(experimentsValue); 
		return stddev;
	}
    // returns lower bound of the 95% confidence interval
	public double confidenceLo()
	{
		return mean - (1.96 * stddev) / Math.sqrt(experimentsValue.length);
	}
	
    // returns upper bound of the 95% confidence interval
	public double confidenceHi()
	{
		return mean + (1.96 * stddev) / Math.sqrt(experimentsValue.length);
	}
	
	// test client, described below
	public static void main(String[] args)
	{
		int totIter = 0;
		int dim = 0;
		
		if (args.length < 2)
			throw new IllegalArgumentException("Too few arguments");
		else 
		{
			dim = convertInt(args[0], "first");
			totIter = convertInt(args[1], "second");
			
			if (Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[1]) <= 0)
				throw new IllegalArgumentException("The 2 arguments cannot be less than 0");
		}
		
		PercolationStats experiment = new PercolationStats(dim, totIter);
		experiment.doExperiment();
	}
	
	private void doExperiment() 
	{
		experimentsValue = new double[iteration];
		
		for (int i = 0; i < iteration; i++)
		{
			Percolation testCase = new Percolation(dimension);
			int openCell = 0;
			
			while (!testCase.percolates())
			{
				int x = StdRandom.uniform(1,dimension + 1);
				int y = StdRandom.uniform(1,dimension + 1);
				
				if (!testCase.isOpen(x, y))
				{
					openCell++;
					testCase.open(x, y);
				}
			}
			
			int numberOfElements = dimension * dimension;
			experimentsValue[i] = (double) openCell / numberOfElements;
			StdOut.println("Test case #" + i + " DONE");
		}
		
		showResult();
	}

	private void showResult() 
	{
		StdOut.println("mean                    = " + mean());
		StdOut.println("stddev                  = " + stddev());
		StdOut.println("95% confidence interval = " + confidenceLo() + " " + confidenceHi());
	}

	private static int convertInt(String str, String field)  
	{
		int d;
		try  
		{	  
			d = Integer.parseInt(str);
		}  
		catch(NumberFormatException nfe)  
		{  
			throw new IllegalArgumentException("The " + field + " is invalid");
		}  
	  
		return d;
	}
}