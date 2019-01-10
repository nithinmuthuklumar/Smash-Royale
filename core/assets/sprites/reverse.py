#reverse.py
#reverses sprites in sprite folders
import glob
import os
from pygame import *
startdir=os.getcwd()
folders=glob.glob("firekirby/*")
print(folders)
flipped={v:[transform.flip(image.load(i),True,False) for i in glob.glob(v+"/right/*.png")] for v in folders}
for v in flipped:
    os.chdir(v+"/left")
    for i,ii in enumerate(flipped[v]):
        image.save(ii,"left"+str(i)+".png")
    os.chdir(startdir)

