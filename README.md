# 目的

***为了满足hive实现自定义行分隔符，因为hive默认的行分割符'\n'是不可以修改的，
但是有些时候又不得已的要直接将数据写到hdfs。将数据直接以textfile形式写到hdfs,
这将会面临着数据里面可能会包含'\n',这样话数据就会错行，导致数据不对，
所以才有了自定义inputformat,具体说明参考[我的博客](https://mp.csdn.net/console/home)***
