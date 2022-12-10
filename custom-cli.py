import click
from cookiecutter.main import cookiecutter
import re
import json

# default values
cookiecutter_json_data = {
    "user": "mcruzdev", 
    "application_name": "service-template"
}

pattern = re.compile(r"[A-Za-z0-9-]+")

# This is a simple validation using regex pattern
def is_valid_name(name):
    return re.fullmatch(pattern, name)

@click.group()
def service_template_cli():
    pass

@click.command()
@click.argument("name")
@click.option("--user", default="mcruzdev", help="Github user")
def generate(name, user):
    click.echo(click.style(
        f"Creating {name} application...", fg="bright_blue"))

    if not is_valid_name(name):
        raise Exception("Invalid application's name!")

    if not is_valid_name(user):
        raise Exception("Invalid Github user!")

    application_name = str.replace(name, '-', '').lower()

    cookiecutter_json_data["application_name"] = application_name
    
    cookiecutter("git@github.com:mcruzdev/service-template.git",
                    extra_context=cookiecutter_json_data,
                    no_input=True,
                    overwrite_if_exists=True)

service_template_cli.add_command(generate)

if __name__ == '__main__':
    service_template_cli()
