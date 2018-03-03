import cv2
import sys
camera = cv2.VideoCapture(0)
run = True
print "[*] press space to take photo"
while run:
    return_value,image = camera.read()
    gray = cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)
    cv2.imshow('Peacce Scout Webcam Grey View',gray)
    if cv2.waitKey(1)& 0xFF == ord(' '):
        cv2.imwrite('qr.jpg',image)
        run = False;
        cv2.destroyAllWindows()
        camera.release()
        del(camera)
    

