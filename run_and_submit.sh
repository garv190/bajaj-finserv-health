#!/bin/bash

echo "🚀 BAJAJ FINSERV SUBMISSION HELPER"
echo "=================================="
echo

# Check if application.yml has been updated
echo "📋 Step 1: Checking configuration..."
if grep -q "Your Full Name" src/main/resources/application.yml; then
    echo "❌ Please update your details in src/main/resources/application.yml"
    echo "   Update: name, regNo, and email with your actual information"
    echo
    exit 1
else
    echo "✅ Configuration appears to be updated"
fi

echo
echo "🔨 Step 2: Building application..."
mvn clean package -q

if [ $? -eq 0 ]; then
    echo "✅ Build successful - JAR created in target/ directory"
else
    echo "❌ Build failed - please check for errors"
    exit 1
fi

echo
echo "📊 Step 3: Running application (this will show the required output)..."
echo "📷 IMPORTANT: Screenshot the terminal output below for submission!"
echo
echo "================================================================================"
echo "                        SCREENSHOT THIS OUTPUT                                  "
echo "================================================================================"

mvn spring-boot:run

echo
echo "================================================================================"
echo "                        END OF SCREENSHOT AREA                                  "
echo "================================================================================"
echo
echo "🎯 Next Steps for Submission:"
echo "1. Create a public GitHub repository named 'bajaj-finserv-sql-solver'"
echo "2. Upload all files to the repository"
echo "3. Create a release and upload the JAR file from target/ directory"
echo "4. Submit the form with your GitHub repository URL and JAR download link"
echo "5. Submit here: https://forms.office.com/r/ZbcqfgSeSw"
echo
echo "📖 For detailed instructions, read SUBMISSION_GUIDE.md"
echo
echo "✅ Your solution is ready for submission!"