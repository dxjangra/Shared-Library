packer {
  required_plugins {
    amazon = {
      version = ">= 0.0.2"
      source  = "github.com/hashicorp/amazon"
    }
  }
}

source "amazon-ebs" "ubuntu_image" {
             ami_name      = "salary-ami"
             source_ami    = "ami-0f5ee92e2d63afc18"
             instance_type = "t2.micro"
             region        = "ap-south-1"
             ssh_username  = "ubuntu"
}

build {
  name = "learn-packer"
  sources = ["source.amazon-ebs.ubuntu"]

  provisioner "file" {
    source      = "provision.sh"
    destination = "/tmp/provision.sh"
  }

  provisioner "shell" {
    inline = [
      "chmod +x /tmp/provision.sh",
      "sudo /tmp/provision.sh",
    ]
  }
}
