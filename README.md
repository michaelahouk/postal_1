# Postal Part 1
This simple application will fetch a Wikipedia article from https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=Cincinnati and output the number of times the string "Cincinnati" appears in the article's text field. 

# To run: 
1. Clone the repo locally
2. Navigate to the project directory
3. Run command `docker build -t postal1 .` to build the image
4. Run command `docker run postal1:latest` to run the image
5. Verify that the information printed to the console is correct 
