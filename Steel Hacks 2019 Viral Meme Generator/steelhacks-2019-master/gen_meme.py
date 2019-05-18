from PIL import ImageFont
from PIL import Image
from PIL import ImageDraw
import pickle
from train_meme import get_img
import pickle
from textgenrnn import textgenrnn

def separate_text(input):
    text_split = input.split()
    num_words = len(text_split)
    middle_index = num_words / 2

    changed = False

    if type(middle_index) is not int:
        changed = True
        middle_index = int(middle_index)

    top_string = ""
    bottom_string = ""
    for i in range(num_words):
        top_string += text_split[i]
        top_string += ' '

        if changed:
            if i == middle_index - 1:
                break
        else:
            if i == middle_index:
                break

    new_range = num_words - middle_index
    for i in range(new_range):
        bottom_string += text_split[i+middle_index]
        bottom_string += ' '

    return top_string, bottom_string


def make_meme(topString, bottomString, filename, name):

    img = Image.open(filename)
    imageSize = img.size

    # find biggest font size that works
    fontSize = int(imageSize[1] / 5)
    font = ImageFont.truetype("impact.ttf", fontSize)
    topTextSize = font.getsize(topString)
    bottomTextSize = font.getsize(bottomString)

    while topTextSize[0] > imageSize[0] - \
            20 or bottomTextSize[0] > imageSize[0] - 20:
        fontSize = fontSize - 1
        font = ImageFont.truetype("impact.ttf", fontSize)
        topTextSize = font.getsize(topString)
        bottomTextSize = font.getsize(bottomString)

    # find top centered position for top text
    topTextPositionX = (imageSize[0] / 2) - (topTextSize[0] / 2)
    topTextPositionY = 0
    topTextPosition = (topTextPositionX, topTextPositionY)

    # find bottom centered position for bottom text
    bottomTextPositionX = (imageSize[0] / 2) - (bottomTextSize[0] / 2)
    bottomTextPositionY = imageSize[1] - bottomTextSize[1]
    bottomTextPosition = (bottomTextPositionX, bottomTextPositionY)

    draw = ImageDraw.Draw(img)

    # draw outlines
    # there may be a better way
    outlineRange = int(fontSize / 15)
    for x in range(-outlineRange, outlineRange + 1):
        for y in range(-outlineRange, outlineRange + 1):
            draw.text((topTextPosition[0] +
                       x, topTextPosition[1] +
                       y), topString, (0, 0, 0), font=font)
            draw.text((bottomTextPosition[0] +
                       x, bottomTextPosition[1] +
                       y), bottomString, (0, 0, 0), font=font)

    draw.text(topTextPosition, topString, (255, 255, 255), font=font)
    draw.text(bottomTextPosition, bottomString, (255, 255, 255), font=font)

    img.save(name)


def main():
    with open('model.pkl', 'rb') as f:
        meme= pickle.load(f)
    
    img = get_img('bear.jpg')
    

    for idx, elem in enumerate(meme):
        top_string, bottom_string = separate_text(elem)
        make_meme(top_string, bottom_string, img, f"dank-meme-{idx}.png")

        

if __name__ == '__main__':
    main()
