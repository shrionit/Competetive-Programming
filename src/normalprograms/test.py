import numpy as np
import re
import matplotlib.pyplot as plt
from matplotlib import style
import pandas as pd
style.use('ggplot')
from sklearn.cluster import KMeans
from sklearn.decomposition import PCA



bdata = {}
with open('../Autism-Screening-Child-Data Plus Description/Autism-Child-Data.arff', 'r') as file:
	for line in file:
		line = line.strip('\n')
		if not line:
			continue
		if line.startswith('@attribute'):
			line = line.split(' ')[1]
			bdata[line] = []
		if not line.startswith('@'):
			linedata = line.split(',')
			if not (len(linedata) == 21):
				continue
			else:
				i=0
				for c in bdata:
					bdata[c].append(linedata[i])
					i = i+1

plt.plot(bdata['A1_Score'], bdata['A2_Score'], c='r')
plt.show()
