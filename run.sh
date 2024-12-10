#!/bin/bash

# Check if both arguments are provided
if [ $# -ne 2 ]; then
  echo "Usage: $0 <day_number> <part_number>"
  exit 1
fi

# Set variables
DAY="day$1"
PART="Part$2"
JAVA_FILE="./$DAY/$PART.java"

# Check if the Java file exists
if [ ! -f "$JAVA_FILE" ]; then
  echo "Error: File $JAVA_FILE does not exist."
  exit 1
fi

# Compile the Java file
javac "$JAVA_FILE"
if [ $? -ne 0 ]; then
  echo "Compilation failed."
  exit 1
fi

# Run the Java program
java -cp "./$DAY" "$PART"
RUN_STATUS=$?

# Remove all .class files in the directory
find "./$DAY" -type f -name "*.class" -exec rm {} \;

# Exit with the status of the Java program
exit $RUN_STATUS