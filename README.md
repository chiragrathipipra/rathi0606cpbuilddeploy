# aws_codebuild
#!/bin/bash
# Update system
sudo yum update -y

# Install dependencies
sudo yum install ruby wget -y

# Download the CodeDeploy agent installer
# Replace 'region-identifier' with your AWS region (e.g., us-east-1, us-west-2, etc.)
cd /home/ec2-user
wget https://aws-codedeploy-us-west-2.s3.us-west-2.amazonaws.com/latest/install

# Make the installer executable
chmod +x ./install

# Install the agent
sudo ./install auto

# Start the service
sudo service codedeploy-agent start

# Enable auto-start on boot
sudo chkconfig codedeploy-agent on


# nohup java -jar AWSCodeDeployDemo-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
