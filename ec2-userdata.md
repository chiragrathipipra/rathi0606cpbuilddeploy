# EC2 User Data Script for Automated CodeDeploy

Add this script as EC2 user data when launching your instance. It will install Java, AWS CLI, and the CodeDeploy agent, then start the agent automatically.

```bash
#!/bin/bash
# Install updates and required packages
sudo yum update -y
sudo yum install -y ruby wget

# Install Java (Corretto 11)
sudo yum install java-11-amazon-corretto -y

# Install AWS CLI (if not already installed)
if ! command -v aws &> /dev/null; then
  curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
  unzip awscliv2.zip
  sudo ./aws/install
fi

# Install CodeDeploy agent
cd /home/ec2-user
wget https://aws-codedeploy-us-east-1.s3.us-east-1.amazonaws.com/latest/install
chmod +x ./install
sudo ./install auto
sudo systemctl enable codedeploy-agent
sudo systemctl start codedeploy-agent

# (Optional) Check agent status
sudo systemctl status codedeploy-agent
```

**Note:**
- Replace the CodeDeploy agent S3 URL with your region if not `us-east-1`.
- This script assumes Amazon Linux 2. For Ubuntu, use `apt` instead of `yum` and adjust Java installation accordingly.
- Add this script as-is in the EC2 launch wizard under "User data".
