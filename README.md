# GymSimulation
Created a gym simulation to demonstrate the correct use of semaphors and threads.

Author: James Spaniak

Start gym simulation via Start.java with the arguments <gym_capacity> and <total_clients> both being integers.

Gym simulation is controlled via a PoolThread with capacity <gym_capacity> and each client is a thread completing their routine. All interleaving permissions are located in Gym.java.

Each client is given a routine between length 15-20 exercises.
Each exercise consists of a specified machine and 0-10 plates per plate size (Total plates: 1-30).
