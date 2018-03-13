XJTLU CSE105 Coursework 3: S1 2017
====
Overview
------
* This is a group management system which has a number of `Community Support Groups`, which bring people together to help each other in the local community. Each group can have up to 500 volunteers. We will call these Groups 1, 2, 3, 4, 5.
* Each volunteer has three skills from a possible 5. The skills are represented by five letters. Each letter is a String.The letters are A B C D E. Each volunteer has three skills. Duplicates are allowed: AAA, BBC, CDE are all possible skillsets.
* We use `Skill Sorter` to automatically allocate a volunteer to a group according to two criteria:
	* The number of each skill within a single group should be fairly well balanced.
	* The total number of all skills in each group will be fairly well balanced.
