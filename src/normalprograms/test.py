import numpy as np
import re
import matplotlib.pyplot as plt
from matplotlib import style
import pandas as pd
style.use('ggplot')
from sklearn.cluster import KMeans
from sklearn.decomposition import PCA



a = ['s', 's', 's', 't', 't']
bdata = [None] * 21
with open('../Data_Clustering/Autism-Screening-Child-Data Plus Description/Autism-Child-Data.arff', 'r') as file:
	for line in file:
		line = line.strip('\n')
		if not line:
			continue
		f = [i for i in list(line.split(' ')[0])]
		if f[0] == '@':
			continue
		linedata = line.split(',')
		for i in range(len(linedata)):
			if bdata[i] == None:
				bdata[i] = []
				bdata[i].append(str(linedata[i]))
			else:
				bdata[i].append(str(linedata[i]))


del bdata[11:21]
for i in range(len(bdata)):
	for j in range(len(bdata[i])):
		if not re.search('[0-9]', bdata[i][j]):
			bdata[i][j] = 0
		else:
			bdata[i][j] = int(bdata[i][j])
bdata = np.array(bdata)
bdata = tuple(map(tuple, bdata))



clf = KMeans(n_clusters = 2)
clf.fit(bdata)

centroids = clf.cluster_centers_
label = clf.labels_

colors = ["r.", "c.", "g.", "k.", "p."]

for i in range(len(bdata)-1):
	plt.plot(bdata[i], bdata[i+1], colors[label[i]], markersize=10)
plt.scatter(centroids[:,0], centroids[:,1], marker="x", s=150, linewidths = 5)
plt.show()