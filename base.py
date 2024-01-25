import cv2
import sys
import time
import numpy as np
import argparse

parser = argparse.ArgumentParser(description = "PEACCE scouting qr reader")
parser.add_argument('-f', dest="file", help="output file", nargs=1, required=True)
parser.add_argument('-d', dest="delay", type=int, help="delay time", nargs=1, required=True)

args=parser.parse_args()
print(f'output file: {args.file[0]}')
print(f'read delay: {args.delay[0]}')
print("press q to quit program")

DELAY = args.delay[0]
FILE_HEADER = f''
FILE_NAME = args.file[0]

f = open(FILE_NAME, "a")
f.write(FILE_HEADER)

def cvCap(webPort):
    camera = cv2.VideoCapture(webPort)
    qrDetector = cv2.QRCodeDetector()
    while True:
        return_value,image = camera.read()
        gray = cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)
        cv2.imshow('Peacce Scout Webcam Grey View',image)
        found, info, pts, _ = qrDetector.detectAndDecodeMulti(gray)
    
        if found:
            if(len(info[0]) < 1):
                print("[*] Read Failed")
                continue
                
            print(info[0])
            f.write(f'{info[0]}\n')
            print(f"[*] Waiting {DELAY} seconds")
            found = False
            cv2.destroyAllWindows()
            camera.release()
            time.sleep(DELAY)
            camera = cv2.VideoCapture(0)
            print("[*] Listening for QR")


        if cv2.waitKey(1)& 0xFF == ord('q'):
            cv2.destroyAllWindows()
            camera.release()
            del(camera)
            break

if __name__ == "__main__":
    cvCap(0)

    

