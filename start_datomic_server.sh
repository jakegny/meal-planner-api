#!/bin/bash

~/datomic/datomic-pro-0.9.5703/bin/run -m datomic.peer-server -h localhost -p 8998 -a myaccesskey,mysecret -d meal_planner,datomic:dev://localhost:4334/meal_planner 
